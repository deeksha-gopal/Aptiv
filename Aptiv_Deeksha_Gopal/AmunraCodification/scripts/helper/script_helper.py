

def convert_string_to_bool(input_string: str):
    """This function will convert a boolean string (e.g. "Yes") to a native Python boolean.
    Any other input types are not handled and will return None.
    Args:
        input_string (str): The string to convert.
    Return:
        True or False (if successful) or None (in case of errors)
    """
    bool_result = None
    if type(input_string) == str:
        if input_string.lower() in ['true', '1', 't', 'y', 'yes']:
            bool_result = True
        elif input_string.lower() in ['false', '0', 'f', 'n', 'no']:
            bool_result = False
        else:
            print("ERROR: The given input string was not recognized!")
    else:
        print("ERROR: The given input parameter is not of type str!")
    return bool_result
