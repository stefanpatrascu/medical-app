FROM node:16 AS build

WORKDIR /app

COPY package.json package-lock.json ./

RUN npm install
RUN npm install -g @angular/cli

COPY . .
