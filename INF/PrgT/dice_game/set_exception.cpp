#pragma once
#include "utils.h"
#include <future>
#include "set_exception.h"

void SetException::producer_thread(std::promise<int>& promise) {
	utils::sleep(1000);

	try {
		throw std::logic_error("some logic_error exception");
		promise.set_value(5);
	}
	catch (...) {
		std::exception_ptr ex_ptr = std::current_exception();
		utils::print("producer_thread: some error ocured");
		promise.set_exception(ex_ptr);
	}

	utils::sleep(1000);

	utils::print("producer_thread: exit thread");
}

void SetException::consumer_thread(std::future<int>& future) {
	utils::print("consumer_thread: waiting for value...");
	utils::time_start("consumer_thread");

	future.wait();

	utils::print("consumer_thread: waited for ", utils::time_end("consumer_thread"), " milliseconds");

	try {
		int value = future.get();
		utils::print("consumer_thread: value received; value = ", value);
	}
	catch (const std::exception& ex) {
		utils::print("consumer_thread: received exception; exception = " + (std::string)ex.what());
	}
}





