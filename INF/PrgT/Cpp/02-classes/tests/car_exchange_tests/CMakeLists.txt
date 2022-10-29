cmake_minimum_required(VERSION 3.18)
project(car_exchange_tests)

add_executable(${PROJECT_NAME} car_tests.cpp exchange_car_tests.cpp)
target_link_libraries(${PROJECT_NAME} lib_car_exchange gtest gtest_main)

set(Labels car-exchange-tests)
gtest_discover_tests(
        ${PROJECT_NAME}
        PROPERTIES LABELS "${Labels}"
        XML_OUTPUT_DIR reports
)