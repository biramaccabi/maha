package com.yahoo.maha.maha_druid_lookups.query.lookup.dynamic.schema;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.protobuf.Descriptors;
import com.yahoo.maha.maha_druid_lookups.query.lookup.namespace.*;
import org.apache.druid.java.util.common.logger.Logger;

import java.io.IOException;

public class DynamicLookupCoreSchemaFactory {

    private static final Logger LOG = new Logger(DynamicLookupCoreSchemaFactory.class);

    public static DynamicLookupCoreSchema buildSchema(DynamicLookupSchema dynamicLookupSchema) throws Descriptors.DescriptorValidationException {

        switch (dynamicLookupSchema.getType()) {
            case PROTOBUF:
                return new DynamicLookupProtobufSchemaSerDe(dynamicLookupSchema);
            case FLAT_BUFFER:
                //dynamicLookupCoreSchema = new DynamicLookupFlatbufferSchemaSerDe(coreSchema);
                return new DynamicLookupFlatbufferSchemaSerDe(dynamicLookupSchema);
            default:
                //should never reach this code
                String error = String.format("Unsupported Schema Type %s for in DynamicLookup schema ", dynamicLookupSchema.getType());
                LOG.error(error);
                throw new IllegalArgumentException(error);
        }
    }
}

