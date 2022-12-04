cmake_minimum_required(VERSION 3.18)
project(student)

set(LIBRARY_NAME "lib_${PROJECT_NAME}")

add_library(
        ${LIBRARY_NAME}
        STATIC
        Student.cpp
        Student.h
)
target_include_directories(
        ${LIBRARY_NAME}
        INTERFACE
        ${CMAKE_CURRENT_SOURCE_DIR}
)
target_link_libraries(${LIBRARY_NAME} Boost::boost)
set_target_properties(${LIBRARY_NAME} PROPERTIES LINKER_LANGUAGE CXX)