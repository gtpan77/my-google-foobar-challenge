import base64

#The encrypted key
message='HFMDFA1UUhQHV0FUFxAABhUAGhAbR1MTDgJbUgYTBQRJFw1HUxUSGlJSChEURkIXEAISFg4cQ0RA\nVEpBSV5ZBAYVBQdVWwJTXEFJVlQPHRUXC1pSCQBXQVQXEBIaHA4NXFIDU1xBSUVWBRYZFR0QF11U\nVxIPUVJAWFBGCFhYQFRKQUlAXglVVxw='

#Your Google username
key='gtpan77'

decrypted_message=[]

#decode the key to base64 bytes
dec_bytes=base64.b64decode(message)

#XOR with Username
for a,b in enumerate(dec_bytes):
    decrypted_message.append(chr(b ^ ord(key[a%len(key)])))

#The encypted message
print("".join(decrypted_message))


"""
{'success' : 'great', 'colleague' : 'esteemed', 'efforts' : 'incredible', 'achievement' : 'unlocked', 'rabbits' : 'safe', 'foo' : 'win!'}
"""