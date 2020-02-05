#!/bin/bash

echo 0 >counter
echo "initial counter value is: $(tr -d '\0' <counter)"

echo "starting 2x counting_process"
./counting_process &
./counting_process

echo "wait until both processes finished"
wait

echo "counter value is: $(tr -d '\0' <counter)"
