FROM ubuntu:latest
LABEL authors="mikl"

ENTRYPOINT ["top", "-b"]