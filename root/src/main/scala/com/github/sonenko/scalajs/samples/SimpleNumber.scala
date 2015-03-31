package com.github.sonenko.scalajs.samples

class SimpleNumber {

  /**
   * pure JS realization example
  function simpleNumbers(count) {
    var acc = [],
        i = 1;
    for (; acc.length < count; i++) {
      isNextSimple(i) && acc.push(i);
      if (i > 100) return acc;
    }

    function isNextSimple(check) {
      var i = 0,
          value = null;
      for (; i < acc.length; i++) {
        value = acc[i];
        if (check % value === 0 && value !== 1) return false;
      }
      return true;
    }

    return acc;
  }
   */


}
