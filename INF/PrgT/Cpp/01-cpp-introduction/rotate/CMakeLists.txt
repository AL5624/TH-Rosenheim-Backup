cmake_minimum_required(VERSION 3.18)
project(rotate)

set(LIBRARY_NAME "lib${PROJECT_NAME}")

add_library(${LIBRARY_NAME} STATIC rotate.h rotate.cpp)
target_include_directories(${LIBRARY_NAME} INTERFACE ${CMAKE_CURRENT_SOURCE_DIR})

add_executable(${PROJECT_NAME} main.cpp)
target_link_libraries(${PROJECT_NAME} PRIVATE ${LIBRARY_NAME})
