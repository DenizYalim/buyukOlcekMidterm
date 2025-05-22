from flask import request, jsonify
from llm import getResponse

app = Flask(__name__)

print(getResponse("hello gpt what's 5+5"))

@app.route("/getResponse", methods=["GET"])
def get_recipeList():
    message = request.params.get("message")
    message = request.params.get("context")
    return getResponse(message, context)


if __name__ == "__main__":
    app.run(port=8000, debug = True)
