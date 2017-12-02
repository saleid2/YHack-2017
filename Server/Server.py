from flask import Flask, request
import static.dataset as ds

app = Flask(__name__)

print('Starting server...')
ds.data_init()

@app.route('/predictuserplan', methods = ['POST'])
def user():
    userinfo = request.data
    return ds.get_prediction(userinfo)


if __name__ == '__main__':
    app.run()
