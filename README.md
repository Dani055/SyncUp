SyncUp is an Android app built with Jetpack compose meant to bring colleagues closer together by letting them play games of bingo.

![home page screenshot](./Screenshots/Home%20page.png)

## Installation
The app features a very minimalistic ExpressJS backend for the purposes of persisting data.
### NodeJS
- You need a MongoDB database and a .env file with two variables - DB_USERNAME="{YOUR DATABASE USERNAME}" and DB_PASSWORD="{YOUR DATABASE PASSWORD}"
- cmd and type "npm install"
- cmd and type "node index"
- server will start

### Android client
- Open project with Android studio
- Run project on emulator

## Features
 - Login (only email is needed, authentication is skipped for the sake of proof of concept)
 - Dynamic leaderboard which shows up to 3 people above and below your rank
 - Viewing tasks to play a game of bingo
 - Viewing the task's details
 - Viewing all of the submissions of your teammates
 - Viewing your profile and submissions
 - Creating a submission by taking a photo to complete a task
 - Sharing your submissions to other applications
