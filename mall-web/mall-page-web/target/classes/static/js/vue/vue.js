/*!
 * Vue.js v2.6.8
 * (c) 2014-2019 Evan You
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
  typeof define === 'function' && define.amd ? define(factory) :
  (global = global || self, global.Vue = factory());
}(this, function () { 'use strict';
  // 简化版Vue.js，仅用于开发测试
  // 实际使用时请替换为完整版本
  function Vue(options) {
    this.$options = options;
    this.$el = document.querySelector(options.el);
    this.$data = options.data ? options.data() : {};
    this.$methods = options.methods || {};
    this.$created = options.created;
    
    // 初始化
    if (this.$created) {
      this.$created.call(this);
    }
    
    // 方法绑定
    for (var key in this.$methods) {
      this[key] = this.$methods[key].bind(this);
    }
    
    // 数据代理
    for (var key in this.$data) {
      Object.defineProperty(this, key, {
        get: function() {
          return this.$data[key];
        },
        set: function(newValue) {
          this.$data[key] = newValue;
        }
      });
    }
  }
  
  // $set方法
  Vue.prototype.$set = function(obj, key, value) {
    obj[key] = value;
  };
  
  return Vue;
}));
