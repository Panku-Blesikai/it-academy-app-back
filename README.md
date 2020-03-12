# SpringBoot and Heroku/TravisCI demo

## Intro

This project was created to show best practises of running a development/testing/deployment pipeline of Spring Boot application using Heroku and Travis CI. Part of Swedbank IT Academy 2020.

## Development

Run `./gradlew bootRun --args='--MONGODB_URI=Your monggo db url'`
or set environment variable to
`MONGODB_URI=Your monggo db url`

After successful start navigate to `http://localhost:8080/`. You have to reboot application to test new changes.

## Testing

Run `./gradlew test`
