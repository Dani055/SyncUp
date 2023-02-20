

module.exports = (req, res, next) => {
  const authHeaders = req.get('Authorization');
  if (!authHeaders) {
    return res.status(401)
      .json({ message: 'Not authenticated.' })
  }

  const userId = req.get('Authorization').split(' ')[1];

  req.userId = userId;
  next();
};