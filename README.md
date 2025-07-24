What It Does
This tool helps multiple servers work together without stepping on each other's toes. It uses Redis to create a "do not disturb" sign that all servers can see.

Why Use It
Stops 2+ instances from same spring boot application from doing the same job at once

Prevents errors when changing shared data

Works for apps running on many computers

How It Works
A server asks Redis: "Can I work on X?"

If nobody else is working on X, Redis says "Yes" and makes a note

When done, the server tells Redis it finished

If a server crashes, Redis automatically cleans up after 5 minutes

Good For
Running scheduled jobs

Updating important data

Processing payments

Any task that should only run once

Needs
Redis server (any recent version)

Basic Java/Spring app
