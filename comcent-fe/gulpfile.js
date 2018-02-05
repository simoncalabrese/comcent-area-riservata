var gulp = require('gulp'),
    gutil = require('gulp-util');

var paths = {
    scripts: ['app/components/**/*.js',
        'app/model/**/*.js', 'app/services/**/*.js', 'app/*.js'],
    index: 'index.js',
    style: 'main.css',
    partials: ['app/components/**/*.html'],
    distDev: './dist'
};