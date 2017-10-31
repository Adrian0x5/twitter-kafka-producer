# twitter-kafka-producer

An app that listens to twitter feed and publish tweets on a kafka stream. New tweets on created-tweets topic and delete events on delete-event-tweets topic

In order to use the app you need to register an app on twitter and get spring.social.twitter.app-id, spring.social.twitter.app-secret
and an accessToken and accessTokenSecret in order to connect to twitter.