# Ready to connect


## Getting started

#### Pre-requisites
1. Ensure you have docker installed 
3. Ensure .env file is created in the root of the project and follows the .env_example format
2. Ensure you are at the root of the project.

#### Commands
1. `docker compose up --build -d`
2. `docker compose exec backend python manage.py makemigrations`
3. `docker compose exec backend python manage.py migrate`
4. `docker compose exec backend python manage.py createsuperuser`

The application should be accessible on port 8000