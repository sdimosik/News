package com.sdimosikvip.common.exceptions

import java.io.IOException

class NoConnectionException() : IOException()

class ServerException : IOException()

class ClientException : IOException()

class UncheckedException : IOException()

class NoCachedDataException : IOException()