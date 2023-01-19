#pragma once
#include <future> 
#include <string>
#include <vector>



class Example {
public:
	virtual void producer_thread(std::promise<int>& promise);
	virtual void consumer_thread(std::future<int>& future);
};