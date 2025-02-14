**** In the root of the repository please execute all this block:

---------------------------------
cd thales-api
docker build -t thales-api-image . 
docker run -d --name thales-api -p 8080:8080 thales-api-image

cd..
cd thales-app
docker build -t thales-app-image .
docker run -d -p 4200:4200 --name thales-app thales-app-image
cd..
---------------------------------

In a browser, go to http://localhost:4200/

Specification of the solution:
I created a backend API to consume the API that you shared in the document.
I implemented JWT authentication. When the homepage loads, it sends a request to the backend to obtain the JWT token, which is then saved in the session storage.
For subsequent requests, the token is sent in the authorization header.
If the token expires, you will need to return to the homepage to get a new one.

I also implemented a loading screen while the frontend waits for the response from the backend.
I modularized the application using lazy loading (it wasn't strictly necessary, but I did it).
In one of my finals commits, I uploaded the unit tests. I didn't cover everything, but I believe I addressed the most important ones.

I hope you like my solution. :)