FROM openjdk:11.0.16-slim
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENV HOST $HOST
ENV PORT $PORT
ENV DB $DB
ENV USER $USER
ENV PASSWORD $PASSWORD
ENV SECRET $SECRET
ENV EXPIRATION $EXPIRATION
ENTRYPOINT ["./wait-for-it.sh", "mysql:3306", "--timeout=0","--",\
            "java", \
            "-cp",                 \
            "app:app/lib/*",                 \
            "com/example/seguridadjwt/SeguridadJwtApplication"]