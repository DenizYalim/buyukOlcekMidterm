from flask import Flask, request, jsonify

app = Flask(__name__)

PYTHON_LINK = "https://localhost:8000" # I wonder what will happen if backend tries to take an already occupied port
SPRING_LINK = "https://localhost:8080"

def proxy_request():
    method = request.method
    headers = {key: value for key, value in request.headers if key != 'Host'}
    data = request.get_data()
    params = request.args

    resp = requests.request(method, target_url, headers=headers, params=params, data=data)
    excluded_headers = ['content-encoding', 'content-length', 'transfer-encoding', 'connection']
    headers = [(name, value) for (name, value) in resp.raw.headers.items() if name.lower() not in excluded_headers]

    return Response(resp.content, resp.status_code, headers)


@app.route("/python/<path:path>", methods = ["GET", "POST", "PATCH", "DELETE"])
def python_proxy(path):
    actual_path = f"{PYTHON_LINK}/{path}"
    return proxy_request(actual_path)

@app.route("/spring/<path:path>", methods = ["GET", "POST", "PATCH", "DELETE"])
def spring_proxy(path):
    actual_path = f"{SPRING_LINK}/{path}"
    return proxy_request(actual_path)

if __name__ == "__main__":
    app.run(port=5000, debug = True) # Note: don't forget to turn off debug later on
