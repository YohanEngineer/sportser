#!/bin/bash

# Attendre que le service MongoDB soit prêt
until mongosh --host 127.0.0.1 -u rootuser -p rootpass --quiet --eval "db.adminCommand('ping')"
do
    echo 'Waiting for MongoDB to start...'
    sleep 1
done

# Créer un nouvel utilisateur
mongosh -u rootuser -p rootpass --host 127.0.0.1 --authenticationDatabase admin --eval '
    db = db.getSiblingDB("sportser");
    db.createUser({
        user: "toto",
        pwd: "password",
        roles: [{
            role: "readWrite",
            db: "sportser"
        }]
    });
'
