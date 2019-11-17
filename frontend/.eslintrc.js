module.exports = {
  root: true,

  parserOptions: {
    parser    : 'babel-eslint',
    sourceType: 'module'
  },

  env: {
    browser: true
  },

  extends: [
    'plugin:vue/essential',
    '@vue/standard'
  ],

  plugins: [
    'vue'
  ],

  globals: {
    'ga'       : true, // Google Analytics
    'cordova'  : true,
    '__statics': true,
    'process'  : true,
    'Capacitor': true
  },

  rules: {
    "key-spacing": [ 0, { "beforeColon": false, "afterColon": false }],
    // allow async-await
    'generator-star-spacing': 'off',

    // allow paren-less arrow functions
    'comma-spacing'       : 'off',
    'arrow-parens'        : 'off',
    'one-var'             : 'off',
    'no-trailing-spaces'  : 'off',
    'no-multi-spaces'     : 'off',
    'import/first'        : 'off',
    'import/named'        : 'error',
    'import/namespace'    : 'error',
    'import/default'      : 'error',
    'import/export'       : 'error',
    'import/extensions'   : 'off',
    'import/no-unresolved': 'off',
    'import/no-extraneous-dependencies' : 'off',
    'prefer-promise-reject-errors'      : 'off',
    'no-multiple-empty-lines'           : 'off',
    'standard/object-curly-even-spacing': 'off',

    // allow console.log during development only
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    // allow debugger during development only
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',

    "semi": 0,
    "skipBlankLines": true,
    "ignoreComments": true,
  }
};
