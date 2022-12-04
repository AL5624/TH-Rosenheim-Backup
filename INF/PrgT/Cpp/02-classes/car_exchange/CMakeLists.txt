cmake_minimum_required(VERSION 3.18)
project(car_exchange)

set(LIBRARY_NAME "lib_${PROJECT_NAME}")

add_library(${LIBRARY_NAME} STATIC Car.h Car.cpp   "classic_threads.cpp")
target_include_directories(${LIBRARY_NAME} INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(${PROJECT_NAME} main.cpp "classic_threads.cpp")
target_link_libraries(${PROJECT_NAME} INTERFACE ${LIBRARY_NAME})