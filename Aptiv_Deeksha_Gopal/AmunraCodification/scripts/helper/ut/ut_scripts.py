import os
import sys

sys.path.append(os.path.abspath(os.path.join(os.path.dirname(os.path.abspath(__file__)), os.pardir)))
from script_helper import convert_string_to_bool

def test_convert_string_to_bool_test01():
    output = convert_string_to_bool("true")
    assert output is True
