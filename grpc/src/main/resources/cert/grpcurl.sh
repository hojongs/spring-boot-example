#!/usr/bin/env bash

export GODEBUG=x509ignoreCN=0

HOST='localhost:6565'
REQUEST='{"name": "hojong"}'
METHOD=helloworld.Greeter/SayHello
echo "grpcurl -d $REQUEST $HOST $METHOD"
grpcurl \
  -insecure \
  -d "$REQUEST" \
  $HOST \
  $METHOD

# -cacert ./backend.cert \
