FROM clojure

EXPOSE 8000

WORKDIR /project
ADD project.clj /project
RUN lein deps

ADD . /project

CMD ["lein", "run", "8000"]
