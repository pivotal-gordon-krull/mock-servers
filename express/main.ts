import express, { Request, Response } from "express";
import dotenv from "dotenv";

// configures dotenv to work in your application
dotenv.config();
const app = express();
app.use(express.json());

const PORT = process.env.PORT;

type UUID = `${string}-${string}-${string}-${string}-${string}`

interface CreateTodoRequest {
  id: UUID;
}

interface Todo {
  id: UUID;
  title: string;
}

const todos: {
  [key: UUID]: Todo
} = {}

app.get("/todos", (_, response: Response) => {
  response.status(200).send(Object.values(todos));
});

app.get("/todos/:id", (request: Request<CreateTodoRequest>, response: Response) => {
  response.status(200).send(todos[request.params.id]);
});

app.post("/todos", (request: Request, response: Response) => {
  const todo = { id: crypto.randomUUID(), title: request.body.title }
  todos[todo.id] = todo
  response.status(200).send(todo);
});


app.listen(PORT, () => {
  console.log("Server running at PORT: ", PORT);
}).on("error", (error) => {
  // gracefully handle error
  throw new Error(error.message);
});