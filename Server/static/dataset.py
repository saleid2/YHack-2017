from sklearn import ensemble
import numpy as np
import pandas as pd
import pickle, os, urllib, json

model = ensemble.RandomForestClassifier(criterion='gini')
model_filename = 'dsm.pkl'


__api_url__ = 'https://v3v10.vitechinc.com/solr/'
__api_subquer_begin__ = 'select?indent=on&q=*:*&wt=json'
__api_subquer_rowcount__ = '&rows=10'
__api_subquer_sort__ = '&sort=id%20desc'
__api_quer__ = __api_subquer_begin__ + __api_subquer_sort__ + __api_subquer_rowcount__
__api_part__ = 'v_participant/'
__api_part_det__ = 'v_participant_detail/'
__api_quot__ = 'v_quotes/'
__api_plan__ = 'v_plan_detail/select?indent=on&q=*:*&wt=json'


def __get_api_data():
    # Fetch v_participant
    res_part_url = __api_url__ + __api_part__ + __api_quer__
    res_part = urllib.request.urlopen(res_part_url)
    participants = json.load(res_part)['response']['docs']

    # Fetch v_participant_detail
    res_part_det_url = __api_url__ + __api_part_det__ + __api_quer__
    res_part_det = urllib.request.urlopen(res_part_det_url)
    part_details = json.load(res_part_det)['response']['docs']

    # Fetch quoted prices and purchased package
    res_quotes_url = __api_url__ + __api_quot__ + __api_quer__
    res_quotes = urllib.request.urlopen(res_quotes_url)
    quotes = json.load(res_quotes)['response']['docs']

    # Build Pandas DataFrame from JSON and cleanup some column(s)
    # OPTIONAL_INSURED : Anything higher than 500 000 is extra coverage
    part_det_pd = pd.DataFrame.from_dict(part_details).drop('MARITAL_STATUS', axis=1).drop('OPTIONAL_INSURED',axis=1)\
        .drop('PEOPLE_COVERED', axis=1).drop('collection_id', axis=1)
    part_pd = pd.DataFrame.from_dict(participants).drop('address', axis=1).drop('DOB', axis=1).drop('email',axis=1)\
        .drop('collection_id', axis=1).drop('latitude', axis=1).drop('longitude', axis=1)
    quotes_pd = pd.DataFrame.from_dict(quotes).drop('collection_id', axis=1)

    # Merge the pandas DataFrames to get 1 big DataFrame containing all useful columns
    full_part = pd.merge(part_pd, part_det_pd, how='outer', on='id')
    quotes_info = pd.merge(full_part, quotes_pd, how='outer', on='id')

    return quotes_info


def __build_model(data):

    return

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