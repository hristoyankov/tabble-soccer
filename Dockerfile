FROM clojure

ENV PORT 8000

EXPOSE 8000

COPY . /project
WORKDIR /project

CMD ["lein", "run", "8000"]
