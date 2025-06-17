import { v4 as uuid } from "uuid";

let users = [];

export const getUsers = (req, res) => {
  res.send(users);
};

export const createUser = (req, res) => {
  const user = req.body;
  const newUser = { ...user, id: uuid() };
  users.push(newUser);

  res.send(`User [${user.username}] added to the database.`);
};

export const getUser = (req, res) => {
  const { id } = req.params;
  const foundUser = users.find((user) => user.id === id);

  if (foundUser) {
    res.send(foundUser);
  } else {
    res.status(404).send(`User with id ${id} not found`);
  }
};

export const deleteUser = (req, res) => {
  const { id } = req.params;
  users = users.filter((user) => user.id !== id);

  res.send(`User with id ${id} deleted from the database.`);
};

export const updateUser = (req, res) => {
  const { id } = req.params;
  const { username, age } = req.body;

  const user = users.find((user) => user.id === id);

  if (user) {
    if (username) user.username = username;
    if (age) user.age = age;

    res.send(`User with id ${id} has been updated.`);
  } else {
    res.status(404).send(`User with id ${id} not found`);
  }
};

export const replaceUser = (req, res) => {
  const { id } = req.params;
  const { username, age } = req.body;

  const userIndex = users.findIndex(user => user.id === id);
  if (userIndex === -1) {
    return res.status(404).send(`User with id ${id} not found`);
  }

  if (!username || !age) {
    return res.status(400).send("Missing required fields: username and age");
  }

  users[userIndex] = { id, username, age };
  res.send(`User with id ${id} has been replaced.`);
};

