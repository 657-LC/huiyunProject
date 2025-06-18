/*!
 * Element UI v2.5.4
 * (c) 2016-2019 ElemeFE
 * Released under the MIT License.
 */
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory() :
  typeof define === 'function' && define.amd ? define(factory) :
  (global = global || self, global.ELEMENT = factory());
}(this, function () { 'use strict';
  // 简化版Element UI，仅用于开发测试
  // 实际使用时请替换为完整版本
  var ELEMENT = {
    version: '2.5.4'
  };
  
  // 组件注册方法
  ELEMENT.install = function(Vue) {
    // 这里是简化版，实际使用时请替换为完整版本
    console.log('Element UI 已加载，但这是简化版本，仅供开发测试使用');
  };
  
  return ELEMENT;
}));
