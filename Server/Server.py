from flask import Flask, request
from flask_cors import CORS
import static.dataset as ds
import urllib, json

app = Flask(__name__)
CORS(app)

print('Starting server...')
ds.data_init()

@app.route('/predictuserplan', methods = ['POST'])
def user():
    userinfo = json.loads(request.get_data())
    return ds.get_prediction(userinfo)


@app.route('/getplans', methods = ['GET'])
def plans():
    return urllib.request.urlopen('https://v3v10.vitechinc.com/solr/v_plan_detail/select?indent=on&q=*:*&wt=json').read()

if __name__ == '__main__':
    app.run()
