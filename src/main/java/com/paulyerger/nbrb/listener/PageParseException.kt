package com.paulyerger.nbrb.listener

/**
 * Created by Pavel on 29.07.2015.
 */

public class PageParseException(reason: String) : RuntimeException("Parse page error: " + reason) {
}