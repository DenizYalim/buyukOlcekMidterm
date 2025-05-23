from flask import Flask, request, jsonify
from llm import getResponse

app = Flask(__name__)

# print(getResponse("hello gpt what's 5+5"))

@app.route("/getResponse", methods=["GET"])
def get_recipeList():
    message = request.args.get("message")
    context = request.args.get("context")
    return getResponse(message, context)


if __name__ == "__main__":
    app.run(port=8000, debug = True)
