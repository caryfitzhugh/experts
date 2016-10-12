#!/bin/bash

grunt dist
rm -rf ../../../resources/public/bootstrap/*
cp -R dist/* ../../../resources/public/bootstrap/
