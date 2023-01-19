#pragma once
#include <mutex>
#include "Future.h"

template <class T>
class SharedState {
private:

	enum State { empty, valid, no_state, ready };

	bool future_already_retrieved = false;

	std::mutex get_future_mutex;

	State state = empty;

	std::mutex state_mutex;

	T value = NULL;

	std::exception* exeption = NULL;

	std::mutex content_mutex;

	std::mutex get;

public:
	/*
	SharedState() {
		this->get.lock();
	};

	bool is_valid() {
		this->state_mutex.lock();

		State state = this->state;

		this->state_mutex.unlock();

		return state == valid;
	};

	void set_state(const State& value) {
		this->state_mutex.lock();

		bool unlock = this->state != ready && value == ready;
		bool lock = this->state == ready && value != ready;

		if (lock) {
			this->get.lock();
		}
		this->state = value;
		if (unlock) {
			this->get.unlock();
		}


		this->state_mutex.unlock();
	};

	T get_value() {
		this->get.lock();
		this->content_mutex.lock();

		T value;
		value = this->value;

		this->content_mutex.unlock();
		this->get.unlock();

		return value;
	};

	void set_value(const T& value) {
		this->content_mutex.lock();

		if (this->exeption == NULL && this->value == NULL) {
			this->value = value;
		}

		this->content_mutex.unlock();
	};

	std::exception* get_exception() {
		this->get.lock();
		this->content_mutex.lock();

		std::exception* ex = this->exeption;

		this->content_mutex.unlock();
		this->get.unlock();

		return ex;
	}

	void set_exception(std::exception* ex) {
		this->content_mutex.lock();

		if (this->exeption == NULL && this->value == NULL) {
			this->exeption = ex;
		}

		this->content_mutex.unlock();
	}*/
};