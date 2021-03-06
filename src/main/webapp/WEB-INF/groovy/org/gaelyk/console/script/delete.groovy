package org.gaelyk.console.script

import groovy.json.JsonBuilder

import org.gaelyk.console.ConsoleScriptRepository


JsonBuilder json = []
response.contentType = 'application/json'

if(!users.userLoggedIn || !users.userAdmin){
	json([deleted: false, exception:"User must be admin!"])
	json.writeTo(out)
	return
}

String name = params.name

if(!name){
	json([deleted: false, exception:"No such script"])
	json.writeTo(out)
	return
}

if(name.endsWith('.groovy')){
    json([exception: "You cannot delete scripts loaded from war directory!"])
    json.writeTo(out)
    return
}


def deleted = ConsoleScriptRepository.delete(name)

json([deleted: true])
json.writeTo(out)