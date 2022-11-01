#include <iostream>       // std::cout
#include <functional>     // std::ref
#include <thread>         // std::thread
#include <future>         // std::promise, std::future
#include "utils.h"
#include <mutex>

void not_working_example() {
	utils::print("Main: creating promise");
	// create promise
	std::promise<int> prom;

	utils::print("Main: getting future from promise");
	// engagement with future
	std::future<int> fut = prom.get_future();

	utils::print("Main: setting value to 5");
	prom.set_value(5);

	int value = fut.get();

	utils::print("Main: value is ", value);

	try {
		utils::print("Main: setting value to 10");
		prom.set_value(10);
	}
	catch (...) {
		utils::print("Main: ups, cannot set value twice");
	}

	try {
		value = fut.get();
		utils::print("Main: value is ", value);
	}
	catch (...) {
		utils::print("Main: ups, cannot read value twice");
	}
}

void working_example() {
	utils::print("Main: creating promise");
	// create promise
	std::promise<int> prom;

	utils::print("Main: getting future from promise");
	// engagement with future
	std::future<int> fut = prom.get_future();

	utils::print("Main: setting value to 5");
	prom.set_value(5);

	int value = fut.get();

	utils::print("Main: value is ", value);

	try {
		utils::print("Main: prom = std::promise<int>()");
		prom = std::promise<int>();
		utils::print("Main: setting value to 10");
		prom.set_value(10);
	}
	catch (...) {
		utils::print("Main: ups, cannot set value twice");
	}

	try {
		utils::print("Main: prom.get_future()");
		fut = prom.get_future();
		value = fut.get();
		utils::print("Main: value is ", value);
	}
	catch (...) {
		utils::print("Main: ups, cannot read value twice");
	}
}

void run_equals_overload() {
	utils::print("\nEquals Overload not Working: \n");
	not_working_example();
	utils::print("\n\n");
	utils::print("\nEquals Overload Working: \n");
	working_example();
}