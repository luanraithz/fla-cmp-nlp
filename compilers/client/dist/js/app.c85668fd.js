(function(t){function e(e){for(var a,o,i=e[0],u=e[1],l=e[2],p=0,h=[];p<i.length;p++)o=i[p],r[o]&&h.push(r[o][0]),r[o]=0;for(a in u)Object.prototype.hasOwnProperty.call(u,a)&&(t[a]=u[a]);c&&c(e);while(h.length)h.shift()();return s.push.apply(s,l||[]),n()}function n(){for(var t,e=0;e<s.length;e++){for(var n=s[e],a=!0,i=1;i<n.length;i++){var u=n[i];0!==r[u]&&(a=!1)}a&&(s.splice(e--,1),t=o(o.s=n[0]))}return t}var a={},r={app:0},s=[];function o(e){if(a[e])return a[e].exports;var n=a[e]={i:e,l:!1,exports:{}};return t[e].call(n.exports,n,n.exports,o),n.l=!0,n.exports}o.m=t,o.c=a,o.d=function(t,e,n){o.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:n})},o.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},o.t=function(t,e){if(1&e&&(t=o(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var n=Object.create(null);if(o.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var a in t)o.d(n,a,function(e){return t[e]}.bind(null,a));return n},o.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return o.d(e,"a",e),e},o.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},o.p="/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],u=i.push.bind(i);i.push=e,i=i.slice();for(var l=0;l<i.length;l++)e(i[l]);var c=u;s.push([0,"chunk-vendors"]),n()})({0:function(t,e,n){t.exports=n("cd49")},"9b19":function(t,e,n){t.exports=n.p+"img/logo.63a7d78d.svg"},ab9d:function(t,e,n){"use strict";var a=n("c99d"),r=n.n(a);r.a},c99d:function(t,e,n){},cd49:function(t,e,n){"use strict";n.r(e);n("cadf"),n("551c"),n("f751"),n("097d");var a=n("2b0e"),r=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-app",[n("v-container",{staticClass:"container",attrs:{fluid:""}},[n("v-row",[n("v-textarea",{attrs:{name:"language"},on:{keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.run(e)}},model:{value:t.content,callback:function(e){t.content=e},expression:"content"}})],1),n("v-row",[n("v-btn",{attrs:{color:"primary"},on:{click:t.run}},[t._v("\n                  Analisar\n              ")]),n("v-btn",{on:{click:t.clear}},[t._v("\n                  Limpar\n              ")]),n("v-dialog",{attrs:{width:"500"},scopedSlots:t._u([{key:"activator",fn:function(e){var a=e.on;return[n("v-btn",t._g({attrs:{color:"red lighten-2",dark:""}},a),[t._v("\n                    Equipe\n                  ")])]}}]),model:{value:t.dialog,callback:function(e){t.dialog=e},expression:"dialog"}},[n("v-card",[n("v-card-title",{staticClass:"headline grey lighten-2 text-center",attrs:{"primary-title":""}},[t._v("\n                    Equipe\n                  ")]),n("v-card-text",[n("br"),t._v("\n                    Luan Raithz Machado\n\n                    "),n("br"),n("a",[t._v("\n                        Github\n                    ")])]),n("v-divider"),n("v-card-actions",[n("div",{staticClass:"flex-grow-1"}),n("v-btn",{attrs:{color:"primary",text:""},on:{click:function(e){t.dialog=!1}}},[t._v("\n                      Fechar\n                    ")])],1)],1)],1)],1)],1),n("v-content",[n("Table",{attrs:{data:t.result}})],1)],1)},s=[],o=(n("8e6e"),n("ac6a"),n("456d"),n("bd86")),i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("v-container",[a("v-layout",{attrs:{"text-center":"",wrap:""}},[a("v-flex",{attrs:{xs12:""}},[a("v-img",{staticClass:"my-3",attrs:{src:n("9b19"),contain:"",height:"200"}})],1),a("v-flex",{attrs:{"mb-4":""}},[a("h1",{staticClass:"display-2 font-weight-bold mb-3"},[t._v("\n        Welcome to Vuetify\n      ")]),a("p",{staticClass:"subheading font-weight-regular"},[t._v("\n        For help and collaboration with other Vuetify developers,\n        "),a("br"),t._v("please join our online\n        "),a("a",{attrs:{href:"https://community.vuetifyjs.com",target:"_blank"}},[t._v("Discord Community")])])]),a("v-flex",{attrs:{"mb-5":"",xs12:""}},[a("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v("What's next?")]),a("v-layout",{attrs:{"justify-center":""}},t._l(t.whatsNext,function(e,n){return a("a",{key:n,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v("\n          "+t._s(e.text)+"\n        ")])}),0)],1),a("v-flex",{attrs:{xs12:"","mb-5":""}},[a("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v("Important Links")]),a("v-layout",{attrs:{"justify-center":""}},t._l(t.importantLinks,function(e,n){return a("a",{key:n,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v("\n          "+t._s(e.text)+"\n        ")])}),0)],1),a("v-flex",{attrs:{xs12:"","mb-5":""}},[a("h2",{staticClass:"headline font-weight-bold mb-3"},[t._v("Ecosystem")]),a("v-layout",{attrs:{"justify-center":""}},t._l(t.ecosystem,function(e,n){return a("a",{key:n,staticClass:"subheading mx-3",attrs:{href:e.href,target:"_blank"}},[t._v("\n          "+t._s(e.text)+"\n        ")])}),0)],1)],1)],1)},u=[],l=a["a"].extend({data:function(){return{ecosystem:[{text:"vuetify-loader",href:"https://github.com/vuetifyjs/vuetify-loader"},{text:"github",href:"https://github.com/vuetifyjs/vuetify"},{text:"awesome-vuetify",href:"https://github.com/vuetifyjs/awesome-vuetify"}],importantLinks:[{text:"Documentation",href:"https://vuetifyjs.com"},{text:"Chat",href:"https://community.vuetifyjs.com"},{text:"Made with Vuetify",href:"https://madewithvuejs.com/vuetify"},{text:"Twitter",href:"https://twitter.com/vuetifyjs"},{text:"Articles",href:"https://medium.com/vuetify"}],whatsNext:[{text:"Explore components",href:"https://vuetifyjs.com/components/api-explorer"},{text:"Select a layout",href:"https://vuetifyjs.com/layout/pre-defined"},{text:"Frequently Asked Questions",href:"https://vuetifyjs.com/getting-started/frequently-asked-questions"}]}}}),c=l,p=n("2877"),h=n("6544"),f=n.n(h),d=n("a523"),v=n("0e8f"),m=n("adda"),b=n("a722"),y=Object(p["a"])(c,i,u,!1,null,null,null),x=y.exports;f()(y,{VContainer:d["a"],VFlex:v["a"],VImg:m["a"],VLayout:b["a"]});var g=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("v-simple-table",[n("thead",[n("tr",[n("th",{staticClass:"text-left"},[t._v("Linha")]),n("th",{staticClass:"text-left"},[t._v("Resultado")]),n("th",{staticClass:"text-left"},[t._v("Sequência")]),n("th",{staticClass:"text-left"},[t._v("Reconhecimento")])])]),n("tbody",t._l(t.data,function(e){return n("tr",[n("td",[t._v(" "+t._s(e.line)+" ")]),n("td",[t._v(" "+t._s(e.result)+" ")]),n("td",[t._v(" "+t._s(e.content)+" ")]),n("td",[t._v(" "+t._s(e.path)+" ")])])}),0)])},_=[],j=a["a"].extend({name:"Table",props:{data:[]}}),O=j,w=n("1f4f"),k=Object(p["a"])(O,g,_,!1,null,null,null),q=k.exports;f()(k,{VSimpleTable:w["a"]});n("6762"),n("7514");var C,S,V,P=n("75fc"),E=n("01c8"),W=(n("7f7f"),n("2fdb"),n("28a5"),n("868f")),I=n("88d3"),T=n("e114"),D=".,;",L="#10";function A(){var t={name:"q0",paths:[]},e={name:"q1q8",paths:[]},n={name:"q0q7",paths:[]},a={name:"q2q10",paths:[]},r={name:"q1q6q9",paths:[]},s={name:"q2q7",paths:[]},o={name:"q3",paths:[]},i={name:"q11",paths:[]},u={name:"q4",paths:[]},l={name:"q5",paths:[],final:!0},c={name:"q12",paths:[],final:!0},p={name:"q13",paths:[]};return t.paths.push({expression:"0",to:e}),t.paths.push({expression:"1",to:n}),t.paths.push({expression:D,to:S}),t.paths.push({expression:".",to:C}),e.paths.push({expression:"#",to:o}),e.paths.push({expression:"0",to:a}),e.paths.push({expression:"1",to:r}),n.paths.push({expression:"#",to:i}),n.paths.push({expression:"0",to:e}),n.paths.push({expression:"1",to:n}),a.paths.push({expression:"#",to:i}),a.paths.push({expression:"0",to:e}),a.paths.push({expression:"1",to:s}),r.paths.push({expression:"#",to:o}),r.paths.push({expression:"0",to:a}),r.paths.push({expression:"1",to:r}),s.paths.push({expression:"#",to:i}),s.paths.push({expression:"0",to:e}),s.paths.push({expression:"1",to:s}),o.paths.push({expression:"0",to:u}),i.paths.push({expression:"0",to:c}),u.paths.push({expression:"0",to:l}),l.paths.push({expression:"0",to:u}),c.paths.push({expression:"0",to:p}),p.paths.push({expression:"0",to:c}),t}C={name:"qError",paths:[],error:!0},S={name:"qSymbol",paths:[{expression:"",to:C}]},S.paths[0].to=S,function(t){t["InvalidWord"]="Invalid Word",t["ValidWord"]="Valid Word",t["InvalidSymbol"]="Invalid Symbol",t["SpecialSymbol"]="Special Symbol"}(V||(V={}));var M,F=function(t){return t.split("\n")},R=Object(W["a"])(F,function(t){return Object(I["a"])(t.map(function(t,e){return Object(I["a"])(t.split(" ").map(function(t){var n=[],a="";function r(t){t&&n.push({content:t,line:e+1})}return t.split("").forEach(function(t){D.includes(t)?(r(a),r(t),a=""):t&&(a+=t.trim())}),r(a),n}))})).filter(Boolean)}),$=function(t){return t.map(function(t){return t.name})},B=Object(T["a"])(function(t,e){return e.map(function(e){var n=e.content,a=e.line,r=function t(e,r,s){var o=Object(E["a"])(r),i=o[0],u=o.slice(1),l=function(t){return{line:a,content:n,result:t,path:$([].concat(Object(P["a"])(s),[e]))}};if(e.error)return l(s.length>1?V.InvalidWord:V.InvalidSymbol);if(!i)return l(e.final?V.ValidWord:e===S?V.SpecialSymbol:V.InvalidWord);var c=e.paths.find(function(t){return t.expression.includes(i)});if(!c){if(L.includes(i))return l(V.InvalidWord);c={to:C,expression:""}}var p=c,h=p.to;return t(h,u.join(""),s.concat([e]))};return r(t,n,[])})}),J=Object(W["a"])(R,B(A()));function N(t,e){var n=Object.keys(t);if(Object.getOwnPropertySymbols){var a=Object.getOwnPropertySymbols(t);e&&(a=a.filter(function(e){return Object.getOwnPropertyDescriptor(t,e).enumerable})),n.push.apply(n,a)}return n}function z(t){for(var e=1;e<arguments.length;e++){var n=null!=arguments[e]?arguments[e]:{};e%2?N(n,!0).forEach(function(e){Object(o["a"])(t,e,n[e])}):Object.getOwnPropertyDescriptors?Object.defineProperties(t,Object.getOwnPropertyDescriptors(n)):N(n).forEach(function(e){Object.defineProperty(t,e,Object.getOwnPropertyDescriptor(n,e))})}return t}var G=(M={},Object(o["a"])(M,V.InvalidWord,"Erro: Palavra inválida"),Object(o["a"])(M,V.ValidWord,"Palavra válida"),Object(o["a"])(M,V.InvalidSymbol,"Símbolo inválido"),Object(o["a"])(M,V.SpecialSymbol,"Símbolo especial"),M),H=function(t){return z({},t,{path:t.path.join(" "),result:G[t.result]})},Q=a["a"].extend({name:"App",components:{HelloWorld:x,Table:q},methods:{run:function(){this.result=J(this.content).map(H)},clear:function(){this.result=[],this.content=""}},data:function(){return{result:[],content:"",expand:!1,dialog:!1}}}),K=Q,U=(n("ab9d"),n("7496")),X=n("8336"),Y=n("b0af"),Z=n("99d9"),tt=n("a75b"),et=n("169a"),nt=n("ce7e"),at=n("0fd9"),rt=n("a844"),st=Object(p["a"])(K,r,s,!1,null,"15e58410",null),ot=st.exports;f()(st,{VApp:U["a"],VBtn:X["a"],VCard:Y["a"],VCardActions:Z["a"],VCardText:Z["b"],VCardTitle:Z["c"],VContainer:d["a"],VContent:tt["a"],VDialog:et["a"],VDivider:nt["a"],VRow:at["a"],VTextarea:rt["a"]});var it=n("f309");a["a"].use(it["a"]);var ut=new it["a"]({icons:{iconfont:"mdi"}});a["a"].config.productionTip=!1,new a["a"]({vuetify:ut,render:function(t){return t(ot)}}).$mount("#app")}});
//# sourceMappingURL=app.c85668fd.js.map