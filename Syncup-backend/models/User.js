const mongoose = require('mongoose');
const Schema = mongoose.Schema;

const userSchema = new Schema({
  username: {
    type: Schema.Types.String,
    required: true
  },
  firstName: {
    type: Schema.Types.String
  },
  lastName: {
    type: Schema.Types.String
  },
  profileImageUrl: {
    type: Schema.Types.String
  },
  points: {
    type: Schema.Types.Number,
  },
  roles: [{type: Schema.Types.String, required: true}]
});


const User = mongoose.model('User', userSchema);

User.seedAdminUser = async () => {
  try {
    let users = await User.find();
    if (users.length > 0) return;
    return User.create({
      username: 'Admin',
      firstName: 'Admin4o',
      lastName: 'Adminov',
      profileImageUrl: "",
      points: 123,
      roles: ['Admin']
    });
  } catch (e) {
    console.log(e);
  }
};

module.exports = User;