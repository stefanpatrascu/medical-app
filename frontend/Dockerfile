FROM node:19-alpine3.16 as builder
WORKDIR "/app"
COPY ./package.json ./
COPY ./package-lock.json ./
RUN npm install
COPY . .

ARG PROFILE
CMD npm run build:$PROFILE

FROM nginx:1.23.3-alpine
EXPOSE 80
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist/medical-app-fe /usr/share/nginx/html
