FROM java:8

WORKDIR /app
ADD ./build/libs/todo-app-backend.jar /app/todo-app-backend.jar
CMD java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n -jar todo-app-backend.jar