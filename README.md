# Spring Boot & React packaging in a single WAR file to deploy Weblogic 12c

## Steps
- build the frontend folder `npm run build`.
- move the content of `dist` to `/src/main/resources/static` folder.
- build the backend `mvn clean install`
- take the .war file from `target` folder and deploy.
