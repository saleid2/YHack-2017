from sklearn import ensemble, preprocessing
import pandas as pd
import pickle, os, urllib, json, timeit
from flatten_json import flatten
from collections import defaultdict

model = ensemble.RandomForestClassifier(criterion='gini')
model_filename = 'dsm.pkl'


__api_url__ = 'https://v3v10.vitechinc.com/solr/'
__api_subquer_begin__ = 'select?indent=on&q=*:*&wt=json'
__api_subquer_rowcount__ = '&rows=100000'
__api_subquer_sort__ = '&sort=id%20desc'
__api_quer__ = __api_subquer_begin__ + __api_subquer_sort__ + __api_subquer_rowcount__
__api_part__ = 'v_participant/'
__api_part_det__ = 'v_participant_detail/'
__api_quot__ = 'v_quotes/'
__api_plan__ = 'v_plan_detail/select?indent=on&q=*:*&wt=json'


def __get_api_data():
    # Fetch v_participant
    print('Fetching v_participant')
    res_part_url = __api_url__ + __api_part__ + __api_quer__

    i = timeit.default_timer()
    res_part = urllib.request.urlopen(res_part_url).read()
    f = timeit.default_timer() - i
    print("Took " + str(f) + "s\n")
    participants = json.loads(res_part)['response']['docs']

    # Fetch v_participant_detail
    print('Fetching v_participant_detail')
    res_part_det_url = __api_url__ + __api_part_det__ + __api_quer__
    i = timeit.default_timer()
    res_part_det = urllib.request.urlopen(res_part_det_url).read()
    f = timeit.default_timer() - i
    print("Took " + str(f) + "s\n")
    part_details = json.loads(res_part_det)['response']['docs']

    # Some magic to flatten the JSON
    for d in part_details:
        if 'PRE_CONDITIONS' in d:
            var = json.loads(d['PRE_CONDITIONS'])
            for c in var:
                c.pop('ICD_CODE')
            d['PRE_CONDITIONS'] = var
    part_det_flat = (flatten(d) for d in part_details)
    part_det_pd = pd.DataFrame(part_det_flat)


    # Fetch quoted prices and purchased package
    print('Fetching v_quotes')
    res_quotes_url = __api_url__ + __api_quot__ + __api_quer__
    i = timeit.default_timer()
    res_quotes = urllib.request.urlopen(res_quotes_url).read()
    f = timeit.default_timer() - i
    print("Took " + str(f) + "s\n")
    quotes = json.loads(res_quotes)['response']['docs']

    # Build Pandas DataFrame from JSON and cleanup some column(s)
    # OPTIONAL_INSURED : Anything higher than 500 000 is extra coverage
    part_det_pd = part_det_pd.drop('MARITAL_STATUS', axis=1).drop('OPTIONAL_INSURED',axis=1)\
        .drop('PEOPLE_COVERED', axis=1).drop('collection_id', axis=1)
    part_pd = pd.DataFrame.from_dict(participants).drop('name',axis=1).drop('address', axis=1).drop('DOB', axis=1)\
        .drop('email',axis=1).drop('collection_id', axis=1).drop('latitude', axis=1).drop('longitude', axis=1)
    quotes_pd = pd.DataFrame.from_dict(quotes).drop('collection_id', axis=1)

    # Merge the pandas DataFrames to get 1 big DataFrame containing all useful columns
    full_part = pd.merge(part_pd, part_det_pd, how='inner', on='id')
    quotes_info = pd.merge(full_part, quotes_pd, how='inner', on='id').drop('id',axis=1)
    return quotes_info


def __build_model(data):
    X = data.drop('PURCHASED',axis=1).drop('PLATINUM',axis=1).drop('GOLD', axis=1).drop('SILVER',axis=1)\
        .drop('BRONZE', axis=1)
    Y = data['PURCHASED'] #data[['PURCHASED','PLATINUM','GOLD','SILVER','BRONZE']]

    le = preprocessing.LabelEncoder()
    enc = {}

    for c in X.columns:
        enc[c] = le.fit_transform(X[c].fillna('Empty'))
        X[c] = le.fit_transform(enc[c])

    print("Starting fitting...")
    start_fit = timeit.default_timer()
    model.fit(X,Y)
    elapsed_fit = timeit.default_timer() - start_fit
    print('Took ' + str(elapsed_fit) + ' seconds')

def data_init():
    if(os.path.isfile(model_filename)):
        print('Saved model found')
        model = pickle.loads(model_filename)
    else:
        print('No saved model found. Fetching from API')
        data = __get_api_data()
        __build_model(data)
    # check if model exists on disk
    # else fetch data from API
    # build model
    # save to disk


def get_prediction(x):
    return model.predict(x)
    # returns model.predict();


data_init()