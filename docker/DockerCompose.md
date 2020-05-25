#### 1. Environment variables in Compose

1. Substitute environment variables in Compose files

   ```
   web:
     image: "webapp:${TAG}"
   ```

2. Set environment variables in containers

   1. You can set environment variables in a service’s containers with the [‘environment’ key](https://docs.docker.com/compose/compose-file/#environment), just like with `docker run -e VARIABLE=VALUE ...`:

   ```
   
   web:
     environment:
       - DEBUG=1
       
   
   ```

3. Pass environment variables to containers

   1. You can pass environment variables from your shell straight through to a service’s containers with the [‘environment’ key](https://docs.docker.com/compose/compose-file/#environment) by not giving them a value, just like with `docker run -e VARIABLE ...`:

   2. ```
      web:
        environment:
          - DEBUG
      ```

4. The “env_file” configuration option

   1. You can pass multiple environment variables from an external file through to a service’s containers with the [‘env_file’ option](https://docs.docker.com/compose/compose-file/#env_file), just like with `docker run --env-file=FILE ...`

      ```
      web:
        env_file:
          - web-variables.env
      ```

5. Set environment variables with ‘docker-compose run’

   Just like with `docker run -e`, you can set environment variables on a one-off container with `docker-compose run -e`:

   ```
   docker-compose run -e DEBUG=1 web python console.py
   ```

   You can also pass a variable through from the shell by not giving it a value:

   ```
   docker-compose run -e DEBUG web python console.py
   ```

   The value of the `DEBUG` variable in the container is taken from the value for the same variable in the shell in which Compose is run.

6. The “.env” file

   You can set default values for any environment variables referenced in the Compose file, or used to configure Compose, in an [environment file](https://docs.docker.com/compose/env-file/) named `.env`:

   ```
   $ cat .env
   TAG=v1.5
   
   $ cat docker-compose.yml
   version: '3'
   services:
     web:
       image: "webapp:${TAG}"
   ```

   When you run `docker-compose up`, the `web` service defined above uses the image `webapp:v1.5`. You can verify this with the [config command](https://docs.docker.com/compose/reference/config/), which prints your resolved application config to the terminal:

   ```
   $ docker-compose config
   
   version: '3'
   services:
     web:
       image: 'webapp:v1.5'
   ```

   Values in the shell take precedence over those specified in the `.env` file. If you set `TAG` to a different value in your shell, the substitution in `image` uses that instead:

   ```
   $ export TAG=v2.0
   $ docker-compose config
   
   version: '3'
   services:
     web:
       image: 'webapp:v2.0'
   ```

   When you set the same environment variable in multiple files, here’s the priority used by Compose to choose which value to use:

   1. Compose file
   2. Shell environment variables
   3. Environment file
   4. Dockerfile
   5. Variable is not defined

#### Example project

https://github.com/dockersamples/example-voting-app