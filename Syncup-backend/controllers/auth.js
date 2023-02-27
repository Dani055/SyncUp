const jwt = require('jsonwebtoken');
const User = require('../models/User');

module.exports = {
  signIn: (req, res, next) => {
    const { email } = req.body;

    User.findOne({ email })
      .then((user) => {
        if (!user) {
          const error = new Error('Wrong email or password!');
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
  },
  getLeaderBoard: async (req, res, next) => {
    try {
      const users = await User.find()
      const sortedUsers = users.sort((u1, u2)=> (u1.points > u2.points) ? -1 : ((u2.points > u1.points) ? 1 : 0))
      res.status(200)
      .json({
        message: 'Fetched leaderboard',
        sortedUsers
      })

    } catch (error) {
      if (!error.statusCode) {
        error.statusCode = 500;
      }
      next(error);
    }
    
  }
};