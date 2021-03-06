import json, os

input_dir = 'input'

for filename in os.listdir(input_dir):
    f = open(input_dir+"/"+filename, "r")
    if filename.split(".")[1] == "json" :
        with open(input_dir+"/"+filename) as json_file:
            data = json.load(json_file)
            selected = False
            if data.get("ingredients"):
                for line in data["ingredients"]:
                    if line.get("ore"):
                        if line["ore"] == "toolCuttingboard":
                            selected = True
                            data["type"] = "tfc:damage_item_shapeless"
                        if line["ore"] == "listAllwater":
                            line["type"] = "tfc:fluid"
                            del line["ore"]
                            line["fluid"] = "fresh_water"
                            selected = True
                        if line.get("ore"):
                            if line["ore"] == "listAllmilk":
                                line["type"] = "tfc:fluid"
                                del line["ore"]
                                line["fluid"] = "milk"
                                selected = True
                        if line.get("ore"):
                            if line["ore"] == "foodVinegar":
                                line["type"] = "tfc:fluid"
                                del line["ore"]
                                line["fluid"] = "vinegar"
                                selected = True

                if selected:
                    with open("output/"+filename, "w") as outfile:
                        json.dump(data, outfile)
    f.close()
    