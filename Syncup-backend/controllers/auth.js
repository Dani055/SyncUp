const jwt = require('jsonwebtoken');
const User = require('../models/User');

module.exports = {
  signIn: (req, res, next) => {
    const { username } = req.body;

    User.findOne({ username })
      .then((user) => {
        if (!user) {
          const error = new Error('Wrong username or password!');
          error.statusCode = 401;
          throw(error);
        }

        res.status(200).json(
          {
            message: 'User successfully logged in!',
            user: user
          });
      })
      .catch(error => {
        if (!error.statusCode) {
          error.statusCode = 500;
        }

        next(error);
      })
  }
};