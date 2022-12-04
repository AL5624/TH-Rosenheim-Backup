cmake_minimum_required(VERSION 3.18)
project(dice_game)

add_executable(dice_game main.cpp     "consumer.cpp" "utils.cpp" "utils.h" "consumer.h" "producer.h" "producer.cpp" "combination.h" "combination.cpp" "equals_overload.h" "equals_overload.cpp")