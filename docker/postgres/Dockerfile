FROM postgres:14.0-alpine

COPY ./init.sql /docker-entrypoint-initdb.d/init.sql

CMD ["docker-entrypoint.sh", "postgres"]