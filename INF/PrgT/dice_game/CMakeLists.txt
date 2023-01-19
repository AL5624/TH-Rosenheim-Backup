cmake_minimum_required(VERSION 3.18)
project(dice_game)

add_executable(dice_game main.cpp      "utils.cpp" "utils.h"        "set_value.cpp" "set_exception.cpp"  "Example.h" "set_value.h" "set_exception.h" "set_value_at_thread_exit.h" "set_value_at_thread_exit.cpp" "share.h" "share.cpp")